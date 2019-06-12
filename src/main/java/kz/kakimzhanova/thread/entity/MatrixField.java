package kz.kakimzhanova.thread.entity;

public class MatrixField {
    private int elem;
    private int modifiedBy = 0;
    private boolean modified = false;

    public MatrixField(int elem){
        this.elem = elem;
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
