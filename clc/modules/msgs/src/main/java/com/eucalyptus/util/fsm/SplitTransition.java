package com.eucalyptus.util.fsm;

import com.eucalyptus.util.HasName;
import com.eucalyptus.util.async.Callback;

/**
 * Simple top/bottom half transition. Delegates
 * {@link TransitionListener#leave()} to {@link #top()} and
 * {@link TransitionListener#enter()} to {@link #bottom()}.
 * @author decker
 * 
 * @param <T>
 * @param <S>
 */
public abstract class SplitTransition<P extends HasName<P>, S extends Enum<S>> extends TransitionAction<P> {
  
  @Override
  public final boolean before( P parent ) {
    return true;
  }
  
  /**
   * @see com.eucalyptus.util.fsm.TransitionListener#leave()
   */
  @Override
  public abstract void leave( P parent, Callback.Completion transitionCallback );
  
  /**
   * @see com.eucalyptus.util.fsm.TransitionListener#enter()
   */
  @Override
  public abstract void enter( P parent );
  
  @Override
  public final void after( P parent ) {}
  
}