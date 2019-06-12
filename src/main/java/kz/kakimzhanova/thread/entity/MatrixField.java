package kz.kakimzhanova.thread.entity;

public class MatrixField {
    private int elem;
    private int i = -1;
    private int j = -1;
    private int modifiedBy = 0;
    private boolean modified = false;

    public MatrixField(int elem, int i, int j){
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

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModified(boolean modified) {
        this.modified = modified;
    }

    public void setElem(int threadId){
        this.elem = threadId;
        this.modified = true;
        this.modifiedBy = threadId;
    }

    public int getElem(){
        return elem;
    }

    public int getModifiedBy(){
        return modifiedBy;
    }

    public boolean isModified(){
        return modified;
    }
}
