package 入門登入介面_finishedAt20180930;

import java.util.HashMap;

public class SaveReference {
    //建立一個例外拋出器，為了要防止多建立一個不必要的SaveReference
    public SaveReference(){
        throw new Error("This is a static class.(SaveReference)");
    }

    //建立HashMap來儲存要傳送的資料(名稱, 物件)
    private static HashMap saveReferences = new HashMap<String, Object>();

    //將物件加入HashMap的方法
    public static void addToSaveReference(String name, Object obj){
        saveReferences.put(name, obj);
    }

    //呼叫存在HashMap內的物件的方法
    public static Object getSaveReference(String name){
        return saveReferences.get(name);
    }
}
