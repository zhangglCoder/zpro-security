package cn.zpro.security.browser.support;


public class ResponesMsg {

    public ResponesMsg(Object context) {
        this.context = context;
    }

    private Object context;

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "ResponesMsg{" +
                "context=" + context +
                '}';
    }
}
