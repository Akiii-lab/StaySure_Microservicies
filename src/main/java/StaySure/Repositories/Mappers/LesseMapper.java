package StaySure.Repositories.Mappers;

import StaySure.Repositories.DTO.LesseDTO;
import StaySure.Repositories.Entitys.Lesse;
import StaySure.Repositories.Entitys.Factories.LesseFactory;

public class LesseMapper {
    
    public static Lesse mapToLisse(LesseDTO lesseDTO) {
        return LesseFactory.createLesse(
            lesseDTO.id,
            lesseDTO.name, 
            lesseDTO.lastName, 
            lesseDTO.phone, 
            lesseDTO.email, 
            lesseDTO.identificationNumber, 
            lesseDTO.birthDate, 
            lesseDTO.password,
            lesseDTO.NitLesse
        );
    }

    public static LesseDTO mapToLisseDTO(Lesse lesse) {
        return new LesseDTO(
            lesse.getId(),
            lesse.getName(),
            lesse.getLastName(),
            lesse.getPhone(),
            lesse.getEmail(),
            lesse.getIdentificationNumber(),
            lesse.getBirthDate(),
            lesse.getPassword(),
            lesse.getNitLesse()
        );
    }
}
