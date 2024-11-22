package StaySure.Services;

import StaySure.Repositories.DTO.CheckerDTO;
import StaySure.Repositories.Entitys.Checker;
import StaySure.Repositories.Mappers.CheckerMapper;

import org.springframework.stereotype.Service;

import StaySure.Repositories.DAO.CheckerDao;

@Service
public class CheckerService {
    private CheckerDao dao;

    public CheckerService() {
        this.dao = new CheckerDao();
    }

    public CheckerDTO createChecker(CheckerDTO checker) {
        Checker new_user = CheckerMapper.mapToChecker(checker);
        Checker saved_user = dao.save(new_user);
        if (saved_user != null) {
            return CheckerMapper.mapToCheckerDTO(saved_user);
        }
        return null;
    }

    public CheckerDTO updateChecker(CheckerDTO checker) {
        Checker new_user = CheckerMapper.mapToChecker(checker);
        Checker saved_user = dao.update(new_user);
        if (saved_user != null) {
            return CheckerMapper.mapToCheckerDTO(saved_user);
        }
        return null;
    }

    public boolean deleteChecker(Long id) {
        return dao.delete(id);
    }

    public CheckerDTO findCheckerById(Long id) {
        Checker user = dao.findById(id);
        if (user != null) {
            return CheckerMapper.mapToCheckerDTO(user);
        }
        return null;
    }

    public CheckerDTO findCheckerByEmail(String email) {
        Checker user = dao.findByEmail(email);
        if (user != null) {
            return CheckerMapper.mapToCheckerDTO(user);
        }
        return null;
    }
}
