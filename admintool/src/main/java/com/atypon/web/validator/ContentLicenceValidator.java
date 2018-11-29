package com.atypon.web.validator;

import com.atypon.web.form.ContentLicenceForm;

import java.util.ArrayList;
import java.util.List;

public class ContentLicenceValidator extends Validator<ContentLicenceForm> {
    @Override
    public List<String> validate(ContentLicenceForm form) {
        List<String> errors = new ArrayList<>();
        if (isEmpty(form.getLicenceName())) {
            errors.add("Licence Name is required");
        }
        if (isEmpty(form.getContentId())) {
            errors.add("Content Id is required");
        }
        return errors;
    }
}
