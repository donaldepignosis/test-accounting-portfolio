/*
 * Copyright 2017 The Mifos Initiative.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.mifos.listener;

import io.mifos.portfolio.api.v1.events.*;
import io.mifos.core.lang.config.TenantHeaderFilter;
import io.mifos.core.test.listener.EventRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author Myrle Krantz
 */
@SuppressWarnings("unused")
@Component
public class PortfolioEventListener {

  private final EventRecorder eventRecorder;

  @Autowired
  public PortfolioEventListener(final EventRecorder eventRecorder) {
    super();
    this.eventRecorder = eventRecorder;
  }

  @JmsListener(
          subscription = EventConstants.DESTINATION,
          destination = EventConstants.DESTINATION,
          selector = EventConstants.SELECTOR_POST_PRODUCT
  )
  public void onCreateProduct(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                              final String payload) {
    this.eventRecorder.event(tenant, EventConstants.POST_PRODUCT, payload, String.class);
  }

  @JmsListener(
          subscription = EventConstants.DESTINATION,
          destination = EventConstants.DESTINATION,
          selector = EventConstants.SELECTOR_PUT_PRODUCT
  )
  public void onChangeProduct(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                              final String payload) {
    this.eventRecorder.event(tenant, EventConstants.PUT_PRODUCT, payload, String.class);
  }

  @JmsListener(
          subscription = EventConstants.DESTINATION,
          destination = EventConstants.DESTINATION,
          selector = EventConstants.SELECTOR_PUT_PRODUCT_ENABLE
  )
  public void onEnableProduct(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                              final String payload) {
    this.eventRecorder.event(tenant, EventConstants.PUT_PRODUCT_ENABLE, payload, String.class);
  }

  @JmsListener(
      subscription = EventConstants.DESTINATION,
      destination = EventConstants.DESTINATION,
      selector = EventConstants.SELECTOR_PUT_CHARGE_DEFINITION
  )
  public void onChangeProductChargeDefinition(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                                              final String payload) {
    this.eventRecorder.event(tenant, EventConstants.PUT_CHARGE_DEFINITION, payload, ChargeDefinitionEvent.class);
  }

  @JmsListener(
      subscription = EventConstants.DESTINATION,
      destination = EventConstants.DESTINATION,
      selector = EventConstants.SELECTOR_POST_TASK_DEFINITION
  )
  public void onCreateTaskDefinition(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                                     final String payload) {
    this.eventRecorder.event(tenant, EventConstants.POST_TASK_DEFINITION, payload, TaskDefinitionEvent.class);
  }

  @JmsListener(
      subscription = EventConstants.DESTINATION,
      destination = EventConstants.DESTINATION,
      selector = EventConstants.SELECTOR_POST_CASE
  )
  public void onCreateCase(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                           final String payload) {
    this.eventRecorder.event(tenant, EventConstants.POST_CASE, payload, CaseEvent.class);
  }

  @JmsListener(
      subscription = EventConstants.DESTINATION,
      destination = EventConstants.DESTINATION,
      selector = EventConstants.SELECTOR_PUT_TASK_INSTANCE_EXECUTION
  )
  public void onChangeTaskInstanceE(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                                    final String payload) {
    this.eventRecorder.event(tenant, EventConstants.PUT_TASK_INSTANCE_EXECUTION, payload, TaskInstanceEvent.class);
  }
}
