package Java.Testcases;

import java.util.ArrayList;
import java.util.List;

import Java.Data.Drinks.Espresso;
import org.junit.Test;

import Java.GsonUtil;
import Java.Beans.OrderBean;
import Java.Beans.UserResponseBean;
import Java.Data.Address;
import Java.Data.Order;
import Java.Data.Responses.UserResponse;
import Java.Domain.OrderManager;

public class ObserverPatternTestCase {
	@Test
	public void test1() {
		OrderManager OM = new OrderManager();
		OrderBean ob = new OrderBean();
		Order inputOrder = new Order();
		System.out.println();
		int orderInputMachineNumber = Integer.parseInt("1");

		String orderInputDrink = "Espresso";
		inputOrder.setDrink(orderInputDrink);
		Address address = OM.findAddressById(orderInputMachineNumber);
		inputOrder.setAddress(address);
		inputOrder.setOrderID(1);
		ob.setOrder(inputOrder);
		String orderJson = GsonUtil.serializeWithGson(ob);
		OM.processOrderWithJson(orderJson);
		String arJson = OM.getResponse();
		UserResponseBean ub = GsonUtil.parseJsonWithGson(arJson, UserResponseBean.class);
		UserResponse ur = ub.getUser_response();
		if (ur.getStatus() == 1) {
			System.out.println("> Order Status: " + ur.getStatus_message());
			System.out.println("> Error: " + ur.getError_message());
		} else {
			System.out.println("> Order Status: " + ur.getStatus_message());
		}
		System.out.println("--------------------------------------------------");
		System.out.println();
		System.out.println();
	}
}
