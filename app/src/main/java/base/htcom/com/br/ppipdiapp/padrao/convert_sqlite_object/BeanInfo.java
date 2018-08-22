package base.htcom.com.br.ppipdiapp.padrao.convert_sqlite_object;


import java.lang.reflect.Field;

/**
 * Created by jsuch2362 on 2014. 5. 14..
 */
public class BeanInfo {

    private Field field;
    private String column;
    private Class type;
    private boolean isReadOnly;

    public BeanInfo(Field field, String column, Class type, boolean isReadOnly) {
        this.field = field;
        this.column = column;
        this.type = type;
        this.isReadOnly = isReadOnly;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public boolean isReadOnly() {
        return isReadOnly;
    }

    public void setReadOnly(boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    @Override
    public String toString() {
        return "BeanInfo{" +
                "field=" + field +
                ", column='" + column + '\'' +
                ", type=" + type +
                ", isReadOnly=" + isReadOnly +
                '}';
    }
}