package bankingsystem.backend.service;

import bankingsystem.backend.config.JwtTokenUtil;
import bankingsystem.backend.dao.UserRepository;
import bankingsystem.backend.dto.Constants;
import bankingsystem.backend.entity.User;
import bankingsystem.backend.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String createUser(User user) {

        if (userRepository.findByContactNo(user.getContactNo()) != null) {
            throw new BadRequestException(Constants.USER_EXIST);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPin(encoder.encode(user.getPin()));

        user.setAge(calculateAge(user.getDob()));

        userRepository.save(user);

        return accountService.createAccount(user);
    }

    private int calculateAge(Date dob) {

        LocalDate birthDate = dob.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public User getUserFromToken(String token) {
        String contactNo = jwtTokenUtil.getUsernameFromToken(token);
        return userRepository.findByContactNo(contactNo);
    }

    public String updateUser(User user) {

        User existing = userRepository.findByContactNo(user.getContactNo());

        if (existing == null) {
            throw new BadRequestException("User not found");
        }

        user.setId(existing.getId());
        user.setPin(existing.getPin());

        user.setAge(calculateAge(user.getDob()));

        userRepository.save(user);

        return Constants.USER_UPDATED;
    }
}