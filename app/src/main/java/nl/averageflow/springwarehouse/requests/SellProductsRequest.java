package nl.averageflow.springwarehouse.requests;

public final class SellProductsRequest {
    private Iterable<SellProductsRequestItem> wantedItemsForSale;

    protected SellProductsRequest() {
    }

    public Iterable<SellProductsRequestItem> getWantedItemsForSale() {
        return this.wantedItemsForSale;
    }
}