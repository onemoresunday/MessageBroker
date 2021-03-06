/******************************************************************************
 * Project        MessageBroker
 * (C) Copyright  2012
 *
 *****************************************************************************
 *
 * @file          AbstractStub.java
 * @author        DBradul
 * 
 ****************************************************************************************/
package org.bench.messagebroker.common;

import org.bench.messagebroker.messaging.GCFMessage;
import org.bench.messagebroker.messaging.MessageBroker;


/****************************************************************************************
 * 
 * Base class for all stubs
 * 
 * Stub is the class used on a server side to communicate with client (see AbstractProxy description).
 * Stub classes derived from this class are generated on the basis of interfaces defined in XML-files (.HBGA). 
 * These interfaces contain definitions for supported methods, events, user-defined data types and other relevant
 * information. See org.onemoresunday.messagebroker.generated.api.SGPimMgrStub as an example.
 * 
 * @see org.bench.messagebroker.generated.api.SGPimMgrStub
 * @author DBradul
 *
 ****************************************************************************************/
public abstract class AbstractStub
{
   protected int mCallId = -1;
   protected MessageBroker mMessageBroker;
   protected String mCurrentSourceAddress;
   protected GCFMessage mRawMessage = null;
   protected String mInstanceName = "";

   /**************************************************************************************
    * Constructor
    */
   protected AbstractStub(  )
   {
      this( null );
   }

   /**************************************************************************************
    * @param instance name. The instance name allows to instantiate several instances
    * of the stub differentiated by the instance name
    */
   protected AbstractStub(String instanceName)
   {
      mInstanceName = instanceName;
   }

   /**************************************************************************************
    * Dispatch the received message (GCF-call) to the corresponding function 
    * that handles this message
    * 
    * @param GCF message to dispatch
    */
   public abstract void dispatchRequest(GCFMessage incMessage);
   
   /**************************************************************************************
    * Dispatch the received message (GCF-abort call) to the corresponding function 
    * that handles this message
    * 
    * @param GCF message to dispatch
    */
   public abstract void dispatchAbort(GCFMessage message);
   
   /**************************************************************************************
    * Set broker that delivers messages to/from the stub
    * 
    * @param router
    */
   public void setBroker(MessageBroker router)
   {
      mMessageBroker = router;
   }

   /**************************************************************************************
    * Get router that delivers messages to/from the stub
    * 
    * @return router
    */
   public MessageBroker getBroker()
   {
      return mMessageBroker;
   }
   
   /**************************************************************************************
    * Return instance name. The instance name allows to instantiate several instances
    * of the stub differentiated by the instance name
    * 
    * @return instance name
    */
   public String getInstanceName()
   {
      return mInstanceName;
   }

   /**************************************************************************************
    * Set instance name
    * 
    * @param instance name
    */
   public void setInstanceName(String instanceName)
   {
      this.mInstanceName = instanceName;
   }
   
   /*************************************************************************************
    * Return currently received GCF-message
    * Valid only within the current overridden 'XXXrequest' method call
    * 
    * @return gcfMessage - raw GCF-message
    */
   public GCFMessage getRawMessage()
   {
      return mRawMessage;
   }

   /*************************************************************************************
    * Set current GCF-message
    * 
    * @param rawMessage - GCF-message to set
    */
   public void setRawMessage(GCFMessage rawMessage)
   {
      mRawMessage = rawMessage;
   }

   /**************************************************************************************
    * Register GCF-calls in router
    * 
    */
   public abstract void registerCalls();
}
