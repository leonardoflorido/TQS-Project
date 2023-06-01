import * as React from "react";
import Typography from "@mui/material/Typography";
import Title from "../../components/Title";

export default function Deposits() {
    const [data, setData] = React.useState({});
    React.useEffect(() => {
        // Fetch pickup orders
        async function fetchOrders() {
            const response = await fetch(
                `http://localhost:8080/orders/get-by-pickup/${localStorage.getItem(
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
            const orders = await response.json();
            // Map data to return an array of objects with the date and the number of orders
            // Date should only consider the day, month and year
            // Example: {date: "2023-10-01", totalOrders: 2}
            // The final array should be sorted by date

            const ordersByDate = orders.reduce((acc, order) => {
                const date = order.date.split("T")[0];
                if (acc[date]) {
                    acc[date]++;
                } else {
                    acc[date] = 1;
                }
                return acc;
            }, {});
            const ordersByDateArray = Object.keys(ordersByDate).map((date) => {
                return {date, totalOrders: ordersByDate[date]};
            });
            const sortedOrdersByDateArray = ordersByDateArray.sort((a, b) => {
                const dateA = new Date(a.date);
                const dateB = new Date(b.date);
                return dateA - dateB;
            });

            const date =
                sortedOrdersByDateArray[sortedOrdersByDateArray.length - 1]
                    .date;
            const totalOrders =
                sortedOrdersByDateArray[sortedOrdersByDateArray.length - 1]
                    .totalOrders;
            setData({date, totalOrders});
        }

        fetchOrders();
    }, []);

    return (
        <React.Fragment>
            <Title>Recent Orders</Title>
            <Typography component="p" variant="h4">
                {data.totalOrders}
            </Typography>
            <Typography color="text.secondary" sx={{flex: 1}}>
                on {data.date}
            </Typography>
        </React.Fragment>
    );
}
