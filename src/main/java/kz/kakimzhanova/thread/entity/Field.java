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

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    void setElem(int workerId){
        this.elem = workerId;
        this.modified = true;
        this.modifiedBy = workerId;
    }

    int getElem(){
        return elem;
    }

    public int getModifiedBy(){
        return modifiedBy;
    }

    public boolean isModified(){
        return modified;
    }

    @Override
    public String toString() {
        return  "elem=" + elem;
    }
}
