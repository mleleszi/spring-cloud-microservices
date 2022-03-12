package com.example.xmlloader.configuration;


import com.example.xmlloader.model.ProductDto;
import com.example.xmlloader.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ProductService productService;

    @Value("${file.path}")
    private String filePath;

    @Bean
    public ItemReader<ProductDto> itemReader() {
        Jaxb2Marshaller productMarshaller = new Jaxb2Marshaller();
        productMarshaller.setClassesToBeBound(ProductDto.class);

        return new StaxEventItemReaderBuilder<ProductDto>()
                .name("productReader")
                .resource(new ClassPathResource(filePath))
                .addFragmentRootElements("product")
                .unmarshaller(productMarshaller)
                .build();
    }

    @Bean
    public Step loadAndSave() {
        return this.stepBuilderFactory
                .get("load")
                .chunk(5)
                .reader(itemReader())
                .writer(products -> products.forEach(product -> productService.save((ProductDto) product)))
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory
                .get("load-and-save")
                .start(loadAndSave())
                .build();
    }
}
