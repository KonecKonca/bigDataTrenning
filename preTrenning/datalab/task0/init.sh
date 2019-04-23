#!/bin/bash

if [[ "$1" == "-h" ]]; then
  echo "Usage: for    `basename $0`:"
  echo "--base  -b    installs updates and get ssh server, curl, wget"
  echo "--java  -j    installs JDK 11"
  echo "--maven -m    installs maven"
  echo "--sql   -s    installs PostgreSQL"
  exit 0
fi

while [[ $# -gt 0 ]]
do
    case $1 in
        '--base' | '-b')
            # Updated the system and installs basic tools needed to execute further commands
            yum -y update
			
			if yum install openssh openssh-server -y
			then 
				echo ".....yum -y install wget performed successfull"
			else
				echo ".....yum -y install wget failed"
			fi
            chkconfig sshd --add

            if yum install curl -y
			then 
				echo "....yum -y install curl performed successfull"
			else
				echo ".....yum -y install curl failed"
			fi
			
            if yum install wget -y
			then 
				echo "....yum -y install wget performed successfull"
			else
				echo ".....yum -y install wget failed"
			fi

            if yum install git -y
			then 
				echo "....yum -y install git performed successfull"
			else
				echo ".....yum -y install git failed"
			fi
            ;;
        '--java' | '-j')
            # Install Java
            echo "======= Installing Java ======="
            if ! type -p java; then
                wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie"   "http://download.oracle.com/otn-pub/java/jdk/11.0.2+9/f51449fcd52f4d52b93a989c5c56ed3c/jdk-11.0.2_linux-x64_bin.rpm"
                 yum localinstall jdk-11.0.2_linux-x64_bin.rpm -y --nogpgcheck
                 rm -rf jdk-*
            fi
            ;;
        '--maven' | '-m')
            echo "======= Installing Maven ======="
            wget http://www-eu.apache.org/dist/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz
            tar -xf apache-maven-3.6.0-bin.tar.gz
            rm -rf apache-maven-3.6.0-bin.tar.gz
            mv apache-maven-3.6.0/ /usr/local/apache-maven/

            echo "======= Exporting Maven Env Variables ======="
            echo 'M2_HOME=/usr/local/apache-maven' >> /etc/profile.d/maven.sh
            echo 'PATH=${M2_HOME}/bin:${PATH}' >> /etc/profile.d/maven.sh
            source /etc/profile.d/maven.sh
            ;;
        '--sql' | '-s')
            # Installing PostgreSQL Server
            echo "======= Installing PostgreSQL ======="
            rpm -Uvh https://yum.postgresql.org/11/redhat/rhel-6-x86_64/pgdg-centos11-11-2.noarch.rpm
            yum install postgresql11-server -y --nogpgcheck

            echo "Starting PostgreSQL service"
            service postgresql-11 initdb
            chkconfig postgresql-11 on
            service postgresql-11 start
            chkconfig postgresql-11 on

            #echo "Setting password for Postgre"
            chmod og+rX /root/andrei/app /root/andrei/init
            sudo -u postgres psql -c "ALTER user postgres WITH PASSWORD 'postgres';"
            service postgresql-11 restart
            ;;
    esac
    shift
done
