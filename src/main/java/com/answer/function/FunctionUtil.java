package com.answer.function;

/**
 * @author answer
 * @version 1.0.0
 * @date 2022/5/13 下午7:52
 */
public class FunctionUtil {

    public static ThrowExceptionFunction isTrue(boolean b){
        return (errorMessage)->{
            if (b){
                throw new RuntimeException(errorMessage);
            }
        };
    }

    public static BranchHandle isTrueOrFalse(boolean b){
        return (trueHandle,falseHandle)->{
            if (b){
                trueHandle.run();
            }else {
                falseHandle.run();
            }
        };
    }

    public static PresentOrElseHandle<?> isBlankOrNotBlank(String str ){
        return (consumer,runnable )->{
            if (str==null || str.length()==0){
                runnable.run();
            }else {
                consumer.accept(str);
            }
        };
    }
}
