import * as React from "react";
import {
	DataGridPremium,
	useGridApiRef,
	useKeepGroupedColumnsHidden,
	GridToolbar,
} from "@mui/x-data-grid-premium";
import Title from "../../components/Title";

export default function Orders() {
	const [data, setData] = React.useState([]);
	React.useEffect(() => {
		async function fetchData() {
			const response = await fetch(
				"http://localhost:8080/orders/get-all",
				{
					method: "GET",
					headers: {
						"Content-Type": "application/json",
					},
				}
			);
			if (response.status !== 200) {
				return;
			}
			const orders = await response.json();

			const res = await fetch("http://localhost:8080/pickup/get-all", {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
				},
			});

			const pickups = await res.json();

			const ordersWithPickup = orders.map((order) => {
				const pickup = pickups.find(
					(pickup) => pickup.id === order.pickupId
				);
				if (pickup) {
					order.pickupName = pickup.name;
				}
				return order;
			});
			setData(ordersWithPickup);
		}
		fetchData();
	}, []);
	const apiRef = useGridApiRef();

	const initialState = useKeepGroupedColumnsHidden({
		apiRef,
		initialState: {
			rowGrouping: {
				model: ["pickupName", "eStore"],
			},
		},
	});

	return (
		<React.Fragment>
			<Title>Orders</Title>
			<DataGridPremium
				initialState={initialState}
				rows={data}
				apiRef={apiRef}
				columns={[
					{ field: "pickupName", headerName: "Pickup", width: 200 },
					{ field: "eStore", headerName: "eStore", flex: 2 },
					{ field: "date", headerName: "Date", flex: 0.5 },
					{ field: "id", headerName: "Order ID", flex: 0.5 },
					{
						field: "status",
						headerName: "Order Status",
						flex: 2,
					},
				]}
				autoHeight
				slots={{ toolbar: GridToolbar }}
				rowGroupingColumnMode="multiple"
			/>
		</React.Fragment>
	);
}
