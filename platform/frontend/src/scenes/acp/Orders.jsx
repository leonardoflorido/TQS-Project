import * as React from "react";
import {DataGridPremium, GridToolbar, useGridApiRef, useKeepGroupedColumnsHidden,} from "@mui/x-data-grid-premium";
import {Button, Stack} from "@mui/material";
import Title from "../../components/Title";

export default function Orders() {
    const [orders, setOrders] = React.useState([]);
    React.useEffect(() => {
        if (localStorage.getItem("pickupId") === null) {
            window.location.href = "/login";
            return;
        }

        // Fetch pickup orders
        async function fetchOrders() {
            const response = await fetch(
                `http://34.175.95.229:8080/orders/get-by-pickup/${localStorage.getItem(
                    "pickupId"
                )}`,
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
            // get number of products
            const transformedData = data.map((order) => {
                const products = order.products;
                const totalProducts = products.reduce((acc, product) => {
                    return acc + product.quantity;
                }, 0);
                return {...order, totalProducts};
            });
            console.log(transformedData);
            setOrders(transformedData);

        }

        fetchOrders();
    }, []);

    const apiRef = useGridApiRef();

    const initialState = useKeepGroupedColumnsHidden({
        apiRef,
        initialState: {
            rowGrouping: {
                model: ["eStore", "Date", "Order ID"],
            },
        },
    });

    const handleSave = () => {
        async function updateOrder(id, status) {
            console.log(id);
            console.log(status);
            await fetch("http://34.175.95.229:8080/orders/update-status", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    id: id,
                    status: status,
                }),
            });
        }

        const allRows = apiRef.current.getRowModels();
        allRows.forEach((key) => {
            updateOrder(key.id, key.status);
        });
    };

    return (
        <React.Fragment>
            <Title>Orders</Title>
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
                    {field: "eStore", headerName: "eStore", width: 150},
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
                    {field: "date", headerName: "Date", flex: 0.5},
                    {field: "id", headerName: "Order ID", flex: 0.5},
                    {
                        field: "customer",
                        headerName: "Customer",
                        flex: 0.5,
                    },
                    {field: "totalProducts", headerName: "Total Products", flex: 0.5},
                ]}
                columnVisibilityModel={{address: false, eStore: false}}
                autoHeight
                slots={{toolbar: GridToolbar}}
                rowGroupingColumnMode="multiple"
            />
        </React.Fragment>
    );
}
