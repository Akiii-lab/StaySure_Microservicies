package StaySure.Repositories.Mappers;

import StaySure.Repositories.DTO.CheckerDTO;
import StaySure.Repositories.Entitys.Checker;
import StaySure.Repositories.Entitys.Factories.CheckerFactory;

public class CheckerMapper {

    public static Checker mapToChecker(CheckerDTO checkerDTO) {
        return CheckerFactory.createChecker(
            checkerDTO.id, 
            checkerDTO.name, 
            checkerDTO.lastName, 
            checkerDTO.phone, 
            checkerDTO.email, 
            checkerDTO.identificationNumber, 
            checkerDTO.birthDate, 
            checkerDTO.password, 
            checkerDTO.startToChecker
            );
    }

    public static CheckerDTO mapToCheckerDTO(Checker checker) {
        return new CheckerDTO(
            checker.getId(),
            checker.getName(), 
            checker.getLastName(), 
            checker.getPhone(), 
            checker.getEmail(), 
            checker.getIdentificationNumber(), 
            checker.getBirthDate(), 
            checker.getPassword(), 
            checker.getStartToChecker()
            );
    }
}
