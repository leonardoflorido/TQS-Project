from rest_framework.decorators import api_view
from rest_framework.response import Response

from .serializers import *


@api_view(['GET'])
def get_products(request):
    products = Product.objects.all()
    serializer = ProductSerializer(products, many=True)
    return Response(serializer.data)
