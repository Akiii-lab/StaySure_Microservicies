package StaySure.Services;

import StaySure.Repositories.DTO.AdminDTO;
import StaySure.Repositories.Entitys.Admin;
import StaySure.Repositories.Mappers.AdminMapper;
import StaySure.Repositories.DAO.AdminDao;

public class AdminService {
    private AdminDao dao;

    public AdminService() {
        this.dao = new AdminDao();
    }

    public AdminDTO createAdmin(AdminDTO admin) {
        Admin new_user = AdminMapper.mapToAdmin(admin);
        Admin saved_user = dao.save(new_user);
        if (saved_user != null) {
            return AdminMapper.mapToAdminDTO(saved_user);
        }
        return null;
    }

    public AdminDTO updateAdmin(AdminDTO admin) {
        Admin new_user = AdminMapper.mapToAdmin(admin);
        Admin saved_user = dao.save(new_user);
        if (saved_user != null) {
            return AdminMapper.mapToAdminDTO(saved_user);
        }
        return null;
    }

    public boolean deleteAdmin(Long id) {
        return dao.delete(id);
    }

    public AdminDTO findAdminById(Long id) {
        Admin user = dao.findById(id);
        if (user != null) {
            return AdminMapper.mapToAdminDTO(user);
        }
        return null;
    }

    public AdminDTO findAdminByEmail(String email) {
        Admin user = dao.findByEmail(email);
        if (user != null) {
            return AdminMapper.mapToAdminDTO(user);
        }
        return null;
    }
}
