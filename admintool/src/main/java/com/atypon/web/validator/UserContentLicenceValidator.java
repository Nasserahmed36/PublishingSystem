package com.atypon.web.validator;

import com.atypon.web.form.UserContentLicenceForm;

import java.util.ArrayList;
import java.util.List;

public class UserContentLicenceValidator extends Validator<UserContentLicenceForm> {
    @Override
    public List<String> validate(UserContentLicenceForm form) {
        List<String> errors = new ArrayList<>();
        if (isEmpty(form.getContentLicenceId())) {
            errors.add("Content Licence ID is required");
        } else if (!isInteger(form.getContentLicenceId())) {
            errors.add("Content Licence ID must be Integer");
        }
        if (isEmpty(form.getUsername())) {
            errors.add("Username is required");
        }
        return errors;
    }
}
