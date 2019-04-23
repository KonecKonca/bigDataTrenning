package com.epam.kozitski.util.mapper;

import com.epam.kozitski.domain.Crime;
import com.epam.kozitski.dto.CrimeDto;

import java.util.List;

public interface CrimeDtoMapper {

    Crime mapToCrime(CrimeDto crimeDto);
    List<Crime> mapToListCrimes(List<CrimeDto> crimeDtoList);

}
