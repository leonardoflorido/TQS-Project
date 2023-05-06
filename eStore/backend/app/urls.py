from django.conf import settings
from django.conf.urls.static import static
from django.urls import path
from .views import ProductView

urlpatterns = [
    path("api/v1/products/", ProductView.as_view()),
]

if settings.DEBUG:
    urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)