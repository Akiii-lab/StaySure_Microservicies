package StaySure.Repositories.Mappers;

import StaySure.Repositories.DTO.AdminDTO;
import StaySure.Repositories.Entitys.Admin;

public class AdminMapper {

    public static Admin mapToAdmin(AdminDTO adminDTO) {
        return new Admin(
                adminDTO.id,
                adminDTO.name,
                adminDTO.lastName,
                adminDTO.phone,
                adminDTO.email,
                adminDTO.identificationNumber,
                adminDTO.birthDate,
                adminDTO.password,
                adminDTO.dateToAdmin,
                adminDTO.fireToAdmin);
    }

    public static AdminDTO mapToAdminDTO(Admin admin) {
        return new AdminDTO(
                admin.getId(),
                admin.getName(),
                admin.getLastName(),
                admin.getPhone(),
                admin.getEmail(),
                admin.getIdentificationNumber(),
                admin.getBirthDate(),
                admin.getPassword(),
                admin.getDateToAdmin(),
                admin.getFireToAdmin());
    }
}
