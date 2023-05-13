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
		company: "Papelaria Gomes",
		address: "Rua das flores, 123",
		date: new Date(2021, 9, 1),
		eStore: "TQS Store",
		orderId: 1234,
		orderStatus: "Delivered, waiting for customer collection",
	},
	{
		id: 1,
		company: "Supermercado Silva",
		address: "Rua das couves, 123",
		date: new Date(2021, 10, 1),
		eStore: "TQS Store",
		orderId: 1235,
		orderStatus: "Delivered, waiting for customer collection",
	},
	{
		id: 2,
		company: "Papelaria Gomes",
		address: "Rua das flores, 123",
		date: new Date(2021, 9, 1),
		eStore: "TQS Store",
		orderId: 1224,
		orderStatus: "Processing",
	},
];

export default function Orders() {
	const apiRef = useGridApiRef();

	const initialState = useKeepGroupedColumnsHidden({
		apiRef,
		initialState: {
			rowGrouping: {
				model: ["company", "eStore"],
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
					{ field: "company", headerName: "Company", width: 200 },
					{ field: "eStore", headerName: "eStore", flex: 2 },
					{ field: "address", headerName: "Address", flex: 0.5 },
					{ field: "date", headerName: "Date", flex: 0.5 },
					{ field: "orderId", headerName: "Order ID", flex: 0.5 },
					{
						field: "orderStatus",
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
