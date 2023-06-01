import * as React from "react";
import {DataGridPremium, GridToolbar, useGridApiRef, useKeepGroupedColumnsHidden,} from "@mui/x-data-grid-premium";
import Title from "../../components/Title";
import {Button, Stack} from "@mui/material";

export default function Pickups() {
    const [rows, setRows] = React.useState([]);
    const apiRef = useGridApiRef();

    React.useEffect(() => {
        if (localStorage.getItem("user") === null) {
            window.location.href = "/amdin/login";
            return;
        }

        async function fetchData() {
            const response = await fetch(
                "http://localhost:8080/pickup/get-all",
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
            const data = await response.json();
            setRows(data);
        }

        fetchData();
    }, []);

    const initialState = useKeepGroupedColumnsHidden({
        apiRef,
        initialState: {},
    });

    const handleSave = () => {
        // Send the rows to the backend
        function updatePikcup(pickup) {
            fetch("http://localhost:8080/pickup/update-status", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(pickup),
            });
        }

        // Get all rows
        const allRows = apiRef.current.getRowModels();
        // Transform the rows map into array of rows map = {key, value}
        allRows.forEach((row) => {
            // Update the row in the backend
            updatePikcup(row);
        });
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
            </Stack>
            <DataGridPremium
                checkboxSelection
                initialState={initialState}
                rows={rows}
                apiRef={apiRef}
                columns={[
                    {field: "name", headerName: "Company", width: 200},
                    {field: "address", headerName: "Address", flex: 1},
                    {
                        field: "status",
                        headerName: "Status",
                        flex: 1,
                        editable: true,
                    },
                ]}
                autoHeight
                slots={{toolbar: GridToolbar}}
                rowGroupingColumnMode="multiple"
            />
        </React.Fragment>
    );
}
