import * as React from "react";
import {useTheme} from "@mui/material/styles";
import {Label, Line, LineChart, ResponsiveContainer, XAxis, YAxis,} from "recharts";
import Title from "../../components/Title";


export default function Chart() {
    const [data, setData] = React.useState([]);

    React.useEffect(() => {
        async function fetchAllOrders() {
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
            setData(sortedOrdersByDateArray);
        }

        fetchAllOrders();
    }, []);

    const theme = useTheme();

    return (
        <React.Fragment>
            <Title>Today</Title>
            <ResponsiveContainer>
                <LineChart
                    data={data}
                    margin={{
                        top: 16,
                        right: 16,
                        bottom: 0,
                        left: 24,
                    }}
                >
                    <XAxis
                        dataKey="date"
                        stroke={theme.palette.text.secondary}
                        style={theme.typography.body2}
                    />
                    <YAxis
                        stroke={theme.palette.text.secondary}
                        style={theme.typography.body2}
                    >
                        <Label
                            angle={270}
                            position="left"
                            style={{
                                textAnchor: "middle",
                                fill: theme.palette.text.primary,
                                ...theme.typography.body1,
                            }}
                        >
                            Orders
                        </Label>
                    </YAxis>
                    <Line
                        isAnimationActive={true}
                        type="monotone"
                        dataKey="totalOrders"
                        stroke={theme.palette.primary.main}
                        dot={true}
                    />
                </LineChart>
            </ResponsiveContainer>
        </React.Fragment>
    );
}
