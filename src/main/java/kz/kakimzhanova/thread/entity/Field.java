package kz.kakimzhanova.thread.entity;

public class Field {
    private int elem;
    private int i;
    private int j;
    private int modifiedBy = 0;
    private boolean modified = false;

    public Field(int elem, int i, int j){
        this.elem = elem;
        this.i = i;
        this.j = j;
    }

    int getI() {
        return i;
    }

    int getJ() {
        return j;
    }

    void setElem(int workerId){
        this.elem = workerId;
        this.modified = true;
        this.modifiedBy = workerId;
    }

    int getElem(){
        return elem;
    }

    public boolean isModified(){
        return modified;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Field{");
        s.append("elem=");
        s.append(elem);
        s.append(", i=");
        s.append(i);
        s.append(", j=");
        s.append(j);
        s.append(", modifiedBy=");
        s.append(modifiedBy);
        s.append('}');
        return s.toString();
    }
}
