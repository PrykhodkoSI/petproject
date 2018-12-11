package com.prykhodkosi.petproject.servletbased.hotel.validator;

import com.prykhodkosi.petproject.servletbased.hotel.exception.ValidationException;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.CreateUserDto;
import com.prykhodkosi.petproject.servletbased.hotel.web.dto.ProfileUserDto;


public class UserValidatorImpl implements UserValidator {

    private static final String INVALID_PASSWORD_MESSAGE = "Invalid user password! Password must include:\r\n" +
            "- a digit must occur at least once\r\n" +
            "- a lower case letter must occur at least once\r\n" +
            "- an upper case letter must occur at least once\r\n" +
            "- a special character must occur at least once (@#$%^&+=)\r\n" +
            "- no whitespace allowed in the entire string\r\n" +
            "- at least eight characters in string";
    private static final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    private StringValidator stringValidator = new StringValidatorImpl();

    @Override
    public void validateNewUser(CreateUserDto createDto) {
        String name = createDto.getName();
        String surname = createDto.getSurname();
        String email = createDto.getEmail();
        String password = createDto.getPassword();
        Integer age = createDto.getAge();
        stringValidator.validateString(name, "Invalid user name: " + name, "exception.validate.user.name", name);
        stringValidator.validateString(surname, "Invalid user surname: " + surname, "exception.validate.user.surname", surname);
        stringValidator.validateString(email, "Invalid user email: " + email, "exception.validate.user.email", email);
        stringValidator.validateWithRegex(email, emailRegex, "Invalid user email: " + email, "exception.validate.user.emailRegex", email);
        stringValidator.validateString(password, INVALID_PASSWORD_MESSAGE, "exception.validate.user.password");
        stringValidator.validateWithRegex(password, passwordRegex, INVALID_PASSWORD_MESSAGE, "exception.validate.user.passwordRegex");
        if (age == null || age < 1 || age > 120) {
            throw new ValidationException("Invalid user age: " + age, "", age != null ? age.toString() : "null");
        }
    }

    @Override
    public void validateUserCredentials(String email, String password) {
        String invalidMessageForEmail = "Invalid user email: " + email;
        stringValidator.validateString(email, invalidMessageForEmail, "exception.validate.user.email", email);
        stringValidator.validateWithRegex(email, emailRegex, invalidMessageForEmail, "exception.validate.user.emailRegex", email);
        stringValidator.validateString(password, "Invalid Password", "exception.validate.user.invalidPassword");
    }


    @Override
    public void validateProfileUserDto(ProfileUserDto createDto) {
        Integer id = createDto.getId();
        String name = createDto.getName();
        String surname = createDto.getSurname();
        String email = createDto.getEmail();
        Integer age = createDto.getAge();
        if(id == null || id < 1){
            throw new ValidationException("Invalid user id: " + id, "exception.validate.user.id", id != null ? id.toString() : null);
        }
        stringValidator.validateString(name,
                "Invalid user name: " + name,
                "exception.validate.user.name", name);
        stringValidator.validateString(surname,
                "Invalid user surname: " + surname,
                "exception.validate.user.surname", surname);
        stringValidator.validateString(email,
                "Invalid user email: " + email,
                "exception.validate.user.email", email);
        stringValidator.validateWithRegex(email,
                emailRegex,
                "Invalid user email: " + email,
                "exception.validate.user.emailRegex", email);
        if (age == null || age < 1 || age > 120) {
            throw new ValidationException("Invalid user age: " + age, "exception.validate.user.age", age != null ? age.toString() : null);
        }
    }
}
