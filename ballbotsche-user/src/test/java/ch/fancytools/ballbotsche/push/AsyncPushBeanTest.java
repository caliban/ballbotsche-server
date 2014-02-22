/*
 * Copyright 2013 caleb.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
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
package ch.fancytools.ballbotsche.push;

import ch.fancytools.ballbotsche.domain.Device;
import ch.fancytools.ballbotsche.domain.Game;
import ch.fancytools.ballbotsche.domain.Player;
import com.google.common.collect.Lists;
import java.util.List;
import javax.enterprise.inject.Instance;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author caleb
 */
public class AsyncPushBeanTest {

    private Push2Mobile androidPushMock = Mockito.mock(Push2Mobile.class);
    private Instance<Push2Mobile> inst = Mockito.mock(Instance.class);

    @Before
    public void setup() {
        Mockito.when(androidPushMock.acceptPushNotficationForType(Mockito.argThat(new BaseMatcher<Device>() {
            @Override
            public boolean matches(Object item) {
                Device dev = (Device) item;
                return dev.getKindOfDevice() == Device.KindOfDeviceEnum.ANDROID;
            }

            @Override
            public void describeTo(Description description) {
            }
        }))).thenReturn(Boolean.TRUE);
        List<Push2Mobile> pushers = Lists.newArrayList(androidPushMock);
        Mockito.when(inst.iterator()).thenReturn(pushers.iterator());
    }

    @Test
    public void simpleAcceptanceTest() {
        AsyncPushBean bean = new AsyncPushBean(inst);

        Player pAndroidOne = Mockito.mock(Player.class);
        Device androidOne = new Device();
        androidOne.setKindOfDevice(Device.KindOfDeviceEnum.ANDROID);
        Mockito.when(pAndroidOne.getDevice()).thenReturn(androidOne);

        Player pAndroidTwo = Mockito.mock(Player.class);
        Device androidTwo = new Device();
        androidTwo.setKindOfDevice(Device.KindOfDeviceEnum.ANDROID);
        Mockito.when(pAndroidTwo.getDevice()).thenReturn(androidTwo);

        Player pAppleOne = Mockito.mock(Player.class);
        Device appleOne = new Device();
        appleOne.setKindOfDevice(Device.KindOfDeviceEnum.APPLE);
        Mockito.when(pAppleOne.getDevice()).thenReturn(appleOne);

        Game game = new Game();
        game.setPlayerList(Lists.newArrayList(pAndroidOne, pAndroidTwo, pAppleOne));
        bean.observe(new EnoughPlayerEvent(game));
        Mockito.verify(androidPushMock, Mockito.times(1)).push(Mockito.argThat(new BaseMatcher<List<Player>>() {
            @Override
            public boolean matches(Object item) {
                List<Player> players = (List<Player>) item;
                Assert.assertEquals(2, players.size());
                Assert.assertEquals(Device.KindOfDeviceEnum.ANDROID, players.get(0).getDevice().getKindOfDevice());
                Assert.assertEquals(Device.KindOfDeviceEnum.ANDROID, players.get(1).getDevice().getKindOfDevice());
                return true;
            }

            @Override
            public void describeTo(Description description) {
            }
        }));
    }
}
