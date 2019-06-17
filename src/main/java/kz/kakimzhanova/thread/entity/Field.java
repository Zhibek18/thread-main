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

    public int getI() {
        return i;
    }

    public int getJ() {
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
        s.append(elem);
        if (isModified()) {
            s.append('(');
            s.append(modifiedBy);
            s.append(')');
        }
        return s.toString();
    }
}
