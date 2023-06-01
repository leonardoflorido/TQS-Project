import * as React from 'react';
import Typography from '@mui/material/Typography';
import Title from '../../components/Title';

function preventDefault(event) {
    event.preventDefault();
}

export default function RecentOrders() {
    const [data, setData] = React.useState([]);
    React.useEffect(() => {
        async function fetchOrders() {
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
            const data = await response.json();
            // Map data to return an array of objects with the date and the number of orders
            // Date should only consider the day, month and year
            // Example: [{"2023-10-01", 2}, {"2023-10-02", 1}"}]
            // The final array should be sorted by date

            const orders = data;
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
            <div>
            </div>
        </React.Fragment>
    );
}