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

package tech.pegasys.artemis.beaconrestapi.beaconhandlers;

import java.util.HashMap;
import java.util.Map;
import tech.pegasys.artemis.beaconrestapi.handlerinterfaces.BeaconRestApiHandler;
import tech.pegasys.artemis.provider.JsonProvider;
import tech.pegasys.artemis.storage.ChainStorageClient;

public class GenesisTimeHandler implements BeaconRestApiHandler {

  private final String returnString;

  public GenesisTimeHandler(final ChainStorageClient client) {
    Map<String, Long> jsonObject = new HashMap<>();
    jsonObject.put("genesis_time", client.getGenesisTime().longValue());
    returnString = JsonProvider.objectToJSON(jsonObject);
  }

  @Override
  public String getPath() {
    return "/node/genesis_time";
  }

  @Override
  public String handleRequest(RequestParams params) {
    return returnString;
  }
}
