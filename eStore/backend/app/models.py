from django.db import models


# Create your models here.
class Product(models.Model):
    id = models.AutoField(primary_key=True)
    productName = models.CharField(max_length=100)
    price = models.IntegerField()
    productImage = models.ImageField(upload_to="images/", default="default_image.jpg")
