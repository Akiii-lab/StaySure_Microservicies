package StaySure.Services;

import org.springframework.stereotype.Service;

import StaySure.Repositories.DTO.LesseDTO;
import StaySure.Repositories.Entitys.Lesse;
import StaySure.Repositories.Mappers.LesseMapper;
import StaySure.Repositories.DAO.LesseDao;

@Service
public class LesseService {
    private LesseDao dao;

    public LesseService() {
        this.dao = new LesseDao();
    }

    public LesseDTO createLesse(LesseDTO lesse) {
        Lesse new_user = LesseMapper.mapToLisse(lesse);
        Lesse saved_user = dao.save(new_user);
        if (saved_user != null) {
            return LesseMapper.mapToLisseDTO(saved_user);
        }
        return null;
    }

    public LesseDTO updateLesse(LesseDTO lesse) {
        Lesse new_user = LesseMapper.mapToLisse(lesse);
        Lesse saved_user = dao.save(new_user);
        if (saved_user != null) {
            return LesseMapper.mapToLisseDTO(saved_user);
        }
        return null;
    }

    public boolean deleteLesse(Long id) {
        return dao.delete(id);
    }

    public LesseDTO findLesseById(Long id) {
        Lesse user = dao.findById(id);
        if (user != null) {
            return LesseMapper.mapToLisseDTO(user);
        }
        return null;
    }

    public LesseDTO findLesseByEmail(String email) {
        Lesse user = dao.findByEmail(email);
        if (user != null) {
            return LesseMapper.mapToLisseDTO(user);
        }
        return null;
    }
}
