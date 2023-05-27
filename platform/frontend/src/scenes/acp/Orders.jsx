import * as React from "react";
import {
	DataGridPremium,
	useGridApiRef,
	useKeepGroupedColumnsHidden,
	GridToolbar,
} from "@mui/x-data-grid-premium";
import { Button, Stack } from "@mui/material";
import Title from "../../components/Title";

const data = [
	{
		id: 0,
		address: "Rua das flores, 123",
		date: new Date(2021, 9, 1),
		eStore: "TQS Store",
		orderId: 1234,
		orderStatus: "Delivered, waiting for customer collection",
		costumer: "Alberto Santos",
	},
	{
		id: 1,
		address: "Rua das couves, 123",
		date: new Date(2021, 10, 1),
		eStore: "TQS Store",
		orderId: 1235,
		orderStatus: "Delivered, waiting for customer collection",
		costumer: "Maria Almeida",
	},
	{
		id: 2,
		address: "Rua das flores, 123",
		date: new Date(2021, 9, 1),
		eStore: "TQS Store",
		orderId: 1224,
		orderStatus: "Processing",
		costumer: "João Silva",
	},
];

export default function Orders() {
	const [orders, setOrders] = React.useState([]);
	React.useEffect(() => {
		// Fetch pickup orders
		async function fetchOrders() {
			const response = await fetch(`http://localhost:8080/order/get_orders?pickupId=${localStorage.getItem("pickupId")}`, {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
				}
			})
			const data = await response.json();
			setOrders(data);
			console.log(data);
		}
		fetchOrders();
	}, []);

	const apiRef = useGridApiRef();

	const initialState = useKeepGroupedColumnsHidden({
		apiRef,
		initialState: {
			rowGrouping: {
				model: ["eStore", "Date", "Products"],
			},
		},
	});

	const handleSave = () => {
		const allRows = apiRef.current.getRowModels();
		async function updateOrder () {
			await fetch(`http://localhost:8080/order/update_order`, {
				method: "PUT",
				headers: {
					"Content-Type": "application/json",
				},
				body: JSON.stringify({
					orderId: allRows[0].orderId,
					orderStatus: allRows[0].orderStatus,
				}),
			});
		}
		const rowsArray = [];
		allRows.forEach((key) => {
			rowsArray.push(key);
		});

		// Send the rows to the backend to update the database
		// TODO
	};

	return (
		<React.Fragment>
			<Title>Recent Orders</Title>
			<Stack direction="row" spacing={1}>
				<Button size="small" onClick={handleSave}>
					Save
				</Button>
			</Stack>
			<DataGridPremium
				initialState={initialState}
				rows={orders}
				apiRef={apiRef}
				columns={[
					{ field: "eStore", headerName: "eStore", width: 150 },
					{
						field: "status",
						headerName: "Order Status",
						width: 150,
						editable: true,
						type: "singleSelect",
						valueOptions: [
							"Processing",
							"In Transit",
							"Waiting for collection",
							"Completed",
						],
					},
					{ field: "date", headerName: "Date", flex: 0.5 },
					{ field: "id", headerName: "Order ID", flex: 0.5 },
					{ field: "customerId", headerName: "CustomerId", flex: 0.5 },
				]}
				columnVisibilityModel={{"address": false, "eStore": false}}
				autoHeight
				slots={{ toolbar: GridToolbar }}
				rowGroupingColumnMode="multiple"
			/>
		</React.Fragment>
	);
}
