/*
 * Copyright 2019 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package tech.pegasys.artemis.networking.p2p.jvmlibp2p.rpc.methods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tech.pegasys.artemis.datastructures.networking.libp2p.rpc.StatusMessage;
import tech.pegasys.artemis.networking.p2p.jvmlibp2p.Peer;
import tech.pegasys.artemis.networking.p2p.jvmlibp2p.rpc.LocalMessageHandler;
import tech.pegasys.artemis.networking.p2p.jvmlibp2p.rpc.ResponseCallback;

public class StatusMessageHandler implements LocalMessageHandler<StatusMessage, StatusMessage> {
  private static final Logger LOG = LogManager.getLogger();
  private final StatusMessageFactory statusMessageFactory;

  public StatusMessageHandler(final StatusMessageFactory statusMessageFactory) {
    this.statusMessageFactory = statusMessageFactory;
  }

  @Override
  public void onIncomingMessage(
      final Peer peer,
      final StatusMessage message,
      final ResponseCallback<StatusMessage> callback) {
    LOG.trace("Peer {} sent status.", peer.getPeerId());
    peer.updateStatus(message);
    callback.respond(statusMessageFactory.createStatusMessage());
    callback.completeSuccessfully();
  }
}
