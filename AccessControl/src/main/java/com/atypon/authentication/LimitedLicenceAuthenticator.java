package com.atypon.authentication;

import com.atypon.domain.ContentLicence;
import com.atypon.domain.UserContentLicence;
import com.atypon.domain.UserRequest;
import com.atypon.service.UserContentLicenceService;

import java.util.Calendar;
import java.util.List;

public class LimitedLicenceAuthenticator implements Authenticator {

    private static final UserContentLicenceService userContentLicenceService =
            AuthenticatorsDependencies.getUserContentLicenceDao();

    @Override
    public boolean hasAccess(String user, UserRequest userRequest, ContentLicence contentLicence) {
        int licencePeriodInMonths;
        try {
            licencePeriodInMonths = Integer.parseInt(contentLicence.getBody());
        } catch (Exception e) {
            return false;
        }
        List<UserContentLicence> userLicences = userContentLicenceService.get(user, contentLicence.getId());
        for (UserContentLicence licence : userLicences) {
            if (isLicenceStillActive(licence, licencePeriodInMonths)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLicenceStillActive(UserContentLicence licence, int licencePeriodInMonths) {
        long finishDate = getFinishDate(licence, licencePeriodInMonths);
        return !hasFinished(finishDate);
    }


    private long getFinishDate(UserContentLicence userContentLicence, int months) {
        Calendar finishDate = Calendar.getInstance();
        finishDate.setTimeInMillis(userContentLicence.getStartDate());
        finishDate.add(Calendar.MONTH, months);
        return finishDate.getTimeInMillis();
    }

    private boolean hasFinished(long finishDate) {
        long now = System.currentTimeMillis();
        return now > finishDate;
    }
}
