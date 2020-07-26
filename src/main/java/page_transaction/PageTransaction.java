package page_transaction;

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
public class PageTransaction implements Serializable {

    @Inject
    private ExternalContext externalContext;

    @Inject
    WebApplicationContext appContext;

    private List<String> transactionEndPages = new ArrayList<>();

    public void begin(final PageTransactionBegin annotation) {
        List<String> tempTransactionEndPages = Arrays.asList(annotation.value()); // このままだと固定長リスト
        this.transactionEndPages = new ArrayList<>(tempTransactionEndPages); // 可変長リストに変換する
    }

    public boolean valid() {

        String requestServletPath = externalContext.getRequestServletPath();
        String requestPage = StringUtils.substringBetween(requestServletPath, appContext.getBaseApplicationPagePath(), "."); // コンテストパスと拡張子を除去

        if (transactionEndPages.contains(requestPage)) {
            transactionEndPages.clear();
            return true;
        }

        transactionEndPages.clear();
        return false;
    }

}
