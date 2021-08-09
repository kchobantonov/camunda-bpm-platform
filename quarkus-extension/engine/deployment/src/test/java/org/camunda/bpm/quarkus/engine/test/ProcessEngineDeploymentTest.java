/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.quarkus.engine.test;

import static org.assertj.core.api.Assertions.assertThat;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

import io.quarkus.test.QuarkusUnitTest;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.quarkus.engine.extension.impl.ProcessEngineStartEvent;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class ProcessEngineDeploymentTest {

  @RegisterExtension
  static final QuarkusUnitTest unitTest = new QuarkusUnitTest()
      .setArchiveProducer(() -> ShrinkWrap.create(JavaArchive.class)
          .addClass(MyConfig.class));

  @ApplicationScoped
  static class MyConfig {

    @Inject
    RepositoryService repositoryService;

    public void createDeployment(@Observes ProcessEngineStartEvent event) {
      repositoryService.createDeployment()
          .addClasspathResource("org/camunda/bpm/quarkus/engine/test/asyncServiceTask.bpmn20.xml")
          .deploy();
    }

  }

  @Inject
  public ProcessEngine processEngine;

  @Test
  public void shouldHaveDeployedResources() {
    // given
    RepositoryService repositoryService = processEngine.getRepositoryService();

    // when
    List<Deployment> deployments = repositoryService.createDeploymentQuery().list();

    // then
    assertThat(deployments).hasSize(1);
  }

}
