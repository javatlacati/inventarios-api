## cookbok

Add a new user to the system as admin

```graphql
mutation {
    addNewSystemUser(
        loginUserWithRole: {
            user: {
                userName: "oscar",
                active: true,
                password: "oscar",
                employeeDetail: {
                    name: "don oscar",
                    lastName: "perez",
                    position: "el mero mero"
                }
            },
            roles: {
                name: "admin",
                permissions: [
                    {
                        name: "AdminMenu"
                    },
                    {
                        name: "AddUser"
                    },
                    {
                        name: "AproveUserCreation"
                    },
                    {
                        name: "AcceptMerchandise"
                    },
                    {
                        name: "ApproveRequisition"
                    },
                    {
                        name: "ViewKitchenInventory"
                    },
                    {
                        name: "GetStatistics"
                    }
                ]
            }
        }
    ) {
        id
        user {
            userName
            active
            employeeDetail {
                name
                lastName
                position
            }
        }
        roles {
            name
            permissions {
                name
            }
        }
    }
}
```

```graphql
{
    hasAccess(userName: "oscar",
        secret: "oscar"
    )
}
```
