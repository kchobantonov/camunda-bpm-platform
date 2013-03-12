/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.rest.impl;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.rest.spi.impl.AbstractProcessEngineAware;

public abstract class AbstractRestProcessEngineAware extends AbstractProcessEngineAware {

  public AbstractRestProcessEngineAware() {
    super();
  }
  
  public AbstractRestProcessEngineAware(String engineName) {
    super(engineName);
  }
  
  protected ProcessEngine getProcessEngine() {
    if (processEngine == null) {
      throw new WebApplicationException(Status.BAD_REQUEST.getStatusCode());
    }
    return processEngine;
  }
}
