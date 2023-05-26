import * as React from "react";
import {
	DataGridPremium,
	useGridApiRef,
	useKeepGroupedColumnsHidden,
	GridToolbar,
} from "@mui/x-data-grid-premium";
import Title from "../../components/Title";
import { Button, Stack } from "@mui/material";

const data = [
	{
		id: 0,
		company: "Papelaria Gomes",
		address: "Rua das flores, 123",
		status: "Partener",
	},
	{
		id: 1,
		company: "Supermercado Silva",
		address: "Rua das couves, 123",
		status: "Partener",
	},
	{
		id: 2,
		company: "Papelaria Jobim",
		address: "Rua das Martas, 123",
		status: "Pending",
	},
];

export default function Pickups() {
	const [rows, setRows] = React.useState([]);
	const apiRef = useGridApiRef();

	React.useEffect(() => {
		async function fetchData() {
			const response = await fetch("http://localhost:8080/acp/get_all", {
				method: "GET",
				headers: {
					"Content-Type": "application/json",
					username: "platform",
					password: '!:s,d>m52""/f(^/-dSR',
				},
			});
			const data = await response.json();
			setRows(data);
		}
		//fetchData();
	}, []);

	const initialState = useKeepGroupedColumnsHidden({
		apiRef,
		initialState: {},
	});

	const createRandomRow = () => {
		return {
			id: rows[rows.length - 1].id + 1,
			name: "Wite the company name here",
			address: "Write your address here",
			status: "Pending",
		};
	};

	const handleSave = () => {
		console.log("SAVE ROWS");
		// Get all rows
		const allRows = apiRef.current.getRowModels();
		// Transform the rows map into array of rows map = {key: {row}}
		const rowsArray = [];
		allRows.forEach((key) => {
			rowsArray.push(key);
		});
		// Send the rows to the backend
		// TODO
	};


	const handleDeleteRow = () => {
		// Get selected rows state
		const selectedRows = apiRef.current.getSelectedRows();
		// For each selected row, delete it from the rows state
		selectedRows.forEach((row) => {
			const index = rows.findIndex((r) => r.id === row.id);
			// Send the row to the backend to delete it
			// TODO

			// Delete the row where the index is the same as the row id
			setRows((prevRows) => [
				...prevRows.slice(0, index),
				...prevRows.slice(index + 1),
			]);
		});
		// Update rows state
	};

	const handleAddRow = () => {
		setRows((prevRows) => [...prevRows, createRandomRow()]);
	};

	return (
		<React.Fragment>
			<Title>ACPs</Title>
			<Stack direction="row" spacing={1}>
				<Button size="small" onClick={handleSave}>
					Save
				</Button>
				<Button size="small" onClick={handleDeleteRow}>
					Delete row
				</Button>
				<Button size="small" onClick={handleAddRow}>
					Add a row
				</Button>
			</Stack>
			<DataGridPremium
				checkboxSelection
				initialState={initialState}
				rows={rows}
				apiRef={apiRef}
				columns={[
					{ field: "name", headerName: "Company", width: 200 },
					{ field: "address", headerName: "Address", flex: 1 },
					{
						field: "status",
						headerName: "Status",
						flex: 1,
						editable: true,
					},
				]}
				autoHeight
				slots={{ toolbar: GridToolbar }}
				rowGroupingColumnMode="multiple"
			/>
		</React.Fragment>
	);
}
