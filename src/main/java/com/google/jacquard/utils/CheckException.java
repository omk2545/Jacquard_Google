package com.google.jacquard.utils;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.WebDriverException;

/**
 * Created by user on 6/27/17.
 */
public class CheckException extends  Exception {




    Exception  exception;

    public CheckException(Exception exception) {
        this.exception = exception;
    }


    public void  chechExceptionIS(Exception exception){


    if(exception instanceof ElementNotFoundException) {





    }   else if(exception instanceof WebDriverException){






        }





    }

}
