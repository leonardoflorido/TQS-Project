import * as React from "react";
import {
	DataGridPremium,
	useGridApiRef,
	useKeepGroupedColumnsHidden,
	GridToolbar,
} from "@mui/x-data-grid-premium";
import Title from "../../components/Title.tsx";

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
	const apiRef = useGridApiRef();

	const initialState = useKeepGroupedColumnsHidden({
		apiRef,
		initialState: {
			rowGrouping: {
				model: ["eStore", "Date"],
			},
		},
	});

	return (
		<React.Fragment>
			<Title>Recent Orders</Title>
			<DataGridPremium
				initialState={initialState}
				rows={data}
				apiRef={apiRef}
				columns={[
					{ field: "eStore", headerName: "eStore", width: 150 },
					{
						field: "orderStatus",
						headerName: "Order Status",
						width: 150,
						editable: true,
					},
					{ field: "address", headerName: "Address", flex: 0.5 },
					{ field: "date", headerName: "Date", flex: 0.5 },
					{ field: "orderId", headerName: "Order ID", flex: 0.5 },
					{ field: "costumer", headerName: "Costumer", flex: 0.5 },
				]}
				autoHeight
				slots={{ toolbar: GridToolbar }}
				rowGroupingColumnMode="multiple"
			/>
		</React.Fragment>
	);
}
