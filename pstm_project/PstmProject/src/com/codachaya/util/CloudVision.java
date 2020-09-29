package com.codachaya.util;

import java.io.FileInputStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/*
import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.services.bigquery.Bigquery.Datasets.Get;
import com.google.api.services.bigquery.model.Dataset;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.DatasetInfo;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobListOption;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.vision.v1.AnnotateFileResponse;
import com.google.cloud.vision.v1.AnnotateFileResponse.Builder;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.AsyncAnnotateFileRequest;
import com.google.cloud.vision.v1.AsyncAnnotateFileResponse;
import com.google.cloud.vision.v1.AsyncBatchAnnotateFilesResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.Block;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.GcsDestination;
import com.google.cloud.vision.v1.GcsSource;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageSource;
import com.google.cloud.vision.v1.InputConfig;
import com.google.cloud.vision.v1.LocalizedObjectAnnotation;
import com.google.cloud.vision.v1.OperationMetadata;
import com.google.cloud.vision.v1.OutputConfig;
import com.google.cloud.vision.v1.Page;
import com.google.cloud.vision.v1.Paragraph;
import com.google.cloud.vision.v1.Symbol;
import com.google.cloud.vision.v1.TextAnnotation;
import com.google.cloud.vision.v1.Word;
import com.google.protobuf.ByteString;
import com.google.protobuf.util.JsonFormat;
*/
import oracle.net.aso.k;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CloudVision {

	public static void main(String[] args) throws Exception {
/*
		// 이미지에서 감지된 엔티티를 반환
=======

		
>>>>>>> c218be7a57701394f7d4fd1c1ad135d7017e6de0:pstm_project/PstmProject/src/com/codachaya/util/CloudVision.java
		ImageAnnotatorClient vision = ImageAnnotatorClient.create();

		String fileName = "C:\\Users\\feelj\\OneDrive\\바탕 화면\\semi\\PSTM\\pstm_project\\PstmProject\\WebContent\\imgfolder\\tomato1.jpg";
		// String fileName = "C:\\Users\\feelj\\OneDrive\\바탕
		// 화면\\semi\\PSTM\\pstm_project\\PstmProject\\WebContent\\img\\515966_540.jpg";

		// Path : 파일을 찾는 개체, 파일 경로를 나타냄
		Path path = Paths.get(fileName);

		// 파일 경로 콘솔에 출력
		System.out.println(path.toAbsolutePath());

		// readAllBytes : 파일의 모든 byte를 읽는다 파일에 오류가 나면 파일이 닫힘
		byte[] data = Files.readAllBytes(path);
		// ByteString : 바이트 스트림으로 표시되는 이미지 콘텐츠
		// copyFrom : 주어진 바이트를 ByteString에 복사
		ByteString imgBytes = ByteString.copyFrom(data);

		// AnnotateImageRequest : 이미지, 사용자 요청 기능 및 컨텍스트 정보
		List<AnnotateImageRequest> requests = new ArrayList<AnnotateImageRequest>();
		// Image : Google Cloud Vision API 작업을 수행 할 클라이언트 이미지
		// setContent : 바이트로 표시되는 이미지
		Image img = Image.newBuilder().setContent(imgBytes).build();
		Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
		AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
		requests.add(request);

		BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
		List<AnnotateImageResponse> responses = response.getResponsesList();
		List<String> result = new ArrayList<String>();
		for (AnnotateImageResponse res : responses) {
			if (res.hasError()) {
				System.out.printf("Error:%s\n", res.getError().getMessage());
				return;
			}
			for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
				
				annotation.getAllFields().forEach((k, v) -> {
					System.out.println(k.toString());
					if (k.toString().equals("google.cloud.vision.v1.EntityAnnotation.description")) {
						result.add(v.toString());
					}
				});
				
			}
			System.out.println(result);
		}*/
	}

}
