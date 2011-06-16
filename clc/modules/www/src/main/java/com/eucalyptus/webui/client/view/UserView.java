package com.eucalyptus.webui.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface UserView extends IsWidget, CanDisplaySearchResult, Clearable {
    
  void setPresenter( Presenter presenter );
  
  public interface Presenter extends SearchRangeChangeHandler, MultiSelectionChangeHandler, KnowsPageSize {
    void onDeleteUsers( );
    void onAddGroups( );
    void onRemoveGroups( );
    void onAddPolicy( );
    void onAddKey( );
    void onAddCert( );
    void onReject( );
    void onApprove( );
  }

}
