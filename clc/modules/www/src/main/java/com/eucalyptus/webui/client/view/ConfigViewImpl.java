package com.eucalyptus.webui.client.view;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.eucalyptus.webui.client.service.SearchResult;
import com.eucalyptus.webui.client.service.SearchResultFieldDesc;
import com.eucalyptus.webui.client.service.SearchResultRow;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class ConfigViewImpl extends Composite implements ConfigView {
  
  private static final Logger LOG = Logger.getLogger( ConfigViewImpl.class.getName( ) );
  
  private static ConfigViewImplUiBinder uiBinder = GWT.create( ConfigViewImplUiBinder.class );
  
  interface ConfigViewImplUiBinder extends UiBinder<Widget, ConfigViewImpl> {}

  @UiField
  LayoutPanel tablePanel;
  
  final SingleSelectionModel<SearchResultRow> selectionModel = new SingleSelectionModel<SearchResultRow>( SearchResultRow.KEY_PROVIDER );
  
  private SearchResultTable table;
  
  private Presenter presenter;
  
  public ConfigViewImpl( ) {
    initWidget( uiBinder.createAndBindUi( this ) );
  }
  
  public void initializeTable( int pageSize,  ArrayList<SearchResultFieldDesc> fieldDescs ) {
    tablePanel.clear( );
    
    selectionModel.addSelectionChangeHandler( new Handler( ) {
      @Override
      public void onSelectionChange( SelectionChangeEvent event ) {
        SearchResultRow row = selectionModel.getSelectedObject( );
        LOG.log( Level.INFO, "Selection changed: " + row );
        presenter.onSelectionChange( row );
      }
    } );
    table = new SearchResultTable( pageSize, fieldDescs, this.presenter, selectionModel );
    tablePanel.add( table );
    table.load( );    
  }

  @Override
  public void showSearchResult( SearchResult result ) {
    if ( this.table == null ) {
      initializeTable( this.presenter.getPageSize( ), result.getDescs( ) );
    }
    table.setData( result );
  }

  @Override
  public void setPresenter( Presenter presenter ) {
    this.presenter = presenter;
  }

  @Override
  public void clear( ) {
    this.tablePanel.clear( );
    this.table = null;
  }

  @Override
  public void clearSelection( ) {
    this.selectionModel.setSelected( this.selectionModel.getSelectedObject( ), false );
  }
  
}
