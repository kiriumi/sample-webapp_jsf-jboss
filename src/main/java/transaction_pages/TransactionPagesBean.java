package transaction_pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import context.WebApplicationContext;

@SessionScoped
public class TransactionPagesBean implements Serializable {

    @Inject
    private ExternalContext externalContext;

    @Inject
    WebApplicationContext appContext;

    private List<String> transactionPages = new ArrayList<>();

    public void begin(final TransactionPages annotation) {

        List<String> tempTransactionPages = Arrays.asList(annotation.value()); // このままだと固定長のリスト
        this.transactionPages = new ArrayList<>(tempTransactionPages); // 可変長のリストに変換する
    }

    public boolean valid() {

        String servletPath = externalContext.getRequestServletPath();
        String requestPage = StringUtils.substringBetween(servletPath, appContext.getBaseApplicationPagePath(), ".");

        if (transactionPages.contains(requestPage)) {
            transactionPages.clear();
            return true;
        }

        transactionPages.clear();
        return false;
    }

}
