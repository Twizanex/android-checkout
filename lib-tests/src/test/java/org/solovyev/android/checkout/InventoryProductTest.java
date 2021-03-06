/*
 * Copyright 2014 serso aka se.solovyev
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
 *
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Contact details
 *
 * Email: se.solovyev@gmail.com
 * Site:  http://se.solovyev.org
 */

package org.solovyev.android.checkout;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.solovyev.android.checkout.Purchase.State.CANCELLED;
import static org.solovyev.android.checkout.Purchase.State.PURCHASED;
import static org.solovyev.android.checkout.Purchase.State.REFUNDED;

@RunWith(RobolectricTestRunner.class)
public class InventoryProductTest {

	@Test
	public void testShouldAddPurchases() throws Exception {
		final Inventory.Product product = new Inventory.Product("test", true);
		product.purchases.add(Purchase.fromJson(PurchaseTest.newJson(0, PURCHASED), null));
		product.purchases.add(Purchase.fromJson(PurchaseTest.newJson(1, CANCELLED), null));

		assertTrue(product.isPurchased("0"));
		assertTrue(product.hasPurchaseInState("1", CANCELLED));
		assertFalse(product.hasPurchaseInState("0", REFUNDED));
		assertFalse(product.hasPurchaseInState("1", REFUNDED));
	}
}