package it.polito.det.springTemplate.constraints;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object>
{
    private String message;
    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(final FieldMatch constraintAnnotation)
    {
        message = constraintAnnotation.message();
        firstFieldName = constraintAnnotation.first();
        secondFieldName = constraintAnnotation.second();
    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        try
        {
            final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

            boolean result = firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
            if(!result) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate( message ).addConstraintViolation();
            }

            return result;
        }
        catch (final Exception ignore)
        {
            // ignore
        }
        return true;
    }
}