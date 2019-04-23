package com.epam.kozitski.util.mapper;

import com.epam.kozitski.domain.Crime;
import com.epam.kozitski.dto.CrimeDto;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CrimeDtoMapperImpl implements CrimeDtoMapper {
    private static final String DATE_FORMAT_PATTERN = "yyyy-mm";

    @Override
    public Crime mapToCrime(CrimeDto crimeDto) {
        return Crime
                .builder()
                .category(crimeDto.getCategory())
                .locationType(crimeDto.getLocationType())
                .location(Crime.Location
                        .builder()
                        .latitude(Double.parseDouble(crimeDto.getLocation().getLatitude()))
                        .longitude(Double.parseDouble(crimeDto.getLocation().getLongitude()))
                        .street(Crime.Location.Street
                                .builder()
                                .id(crimeDto.getLocation().getStreet().getId())
                                .name(crimeDto.getLocation().getStreet().getName())
                                .build()
                        )
                        .build()
                )
                .context(crimeDto.getContext())
                .outcomeStatus(Crime.OutcomeStatus
                        .builder()
                        .category(crimeDto.getCategory())
                        .date(dateToLong(crimeDto.getOutcomeStatus().getDate()))
                        .build()
                )
                .persistentId(crimeDto.getPersistentId())
                .locationSubtype(crimeDto.getLocationSubtype())
                .month(dateToLong(crimeDto.getMonth()))
                .id(crimeDto.getId())
                .build();
    }

    @Override
    public List<Crime> mapToListCrimes(List<CrimeDto> crimeDtoList) {
        List<Crime> result = new LinkedList<>();

        crimeDtoList.forEach(e -> result.add(mapToCrime(e)));

        return result;
    }

    private long dateToLong(String stringDate) {
        long result;

        if(stringDate != null && !stringDate.isEmpty()){
            try {
                DateFormat format = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.ENGLISH);
                Date date = format.parse(stringDate);
                result = date.getTime();
            }
            catch (ParseException e){
                result = Integer.MIN_VALUE;
            }
        }
        else {
            result = Integer.MIN_VALUE;
        }

        return result;
    }

}
